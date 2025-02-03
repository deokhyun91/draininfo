

import React, { useEffect, useRef, useState, } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { motion } from "framer-motion";
import Address from './Address';
import userHook from '../hook/userhook';

export default function UserUpdate(){
    const userData = userHook();
    const history = useNavigate ();
    const [isLoading, setIsLoading] = useState(false);
    const nameRef = useRef(null);
    const idRef = useRef(null);
    const passwordRef = useRef(null);
    const phoneRef = useRef(null);
    const [address1, setAddress1] = useState();
    const [address2, setAddress2] = useState();
    
    const handleDataChange = (newData1,newData2) => {
        setAddress1(newData1);
        setAddress2(newData2);
        
      };

      
    function checkid(){
      
        fetch(`/join/userid?id=` + idRef.current.value,{
            method:"GET"
        }).then(res => {
            if(res.ok){
                
                return res.json();
               
            }else{
             
                return res.json();
               
            }
        }).then((jsons) => {
            console.log(jsons)
            if(jsons.message === "회원가입가능여부"){
                var checkUsernameFlag = jsons.data;
          
       
                if(checkUsernameFlag){
                    alert("사용할 수 있는 아이디입니다.");
                }else{
                    alert("이미 사용중인 아이디입니다.");
                }
            }else{
                var err = jsons.message;
                alert(err);
            }

          
        })
        
    }
    let address;
  
    if(address1 === undefined){
        address = userData.address;
    }else{
        address = address1 + address2;
    }
    
    function onSubmit(e){
        e.preventDefault();
     
        if(!isLoading){
         
            setIsLoading(true);
       
        fetch('/user/update',{
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                num: userData.num,
                name : nameRef.current.value,
                id : idRef.current.value,
                password : passwordRef.current.value,
                phone : phoneRef.current.value,
                address : address
                
            }),
        }).then(res => {
            if(res.ok){
                alert("수정되었습니다 다시 로그인해주세요");
                setIsLoading(false);
             
                window.location.replace("/logout");
                return res.json();
            }else{
                setIsLoading(false);
                return res.json();
               
              
            }
        }).then((jsons) => {
            console.log(jsons)
            var err = jsons.message;
          
            alert(err.replace(/signUp.arg0./g,""));
          });
       
        }
    }
  
    
   
      
    return(
    
        <motion.div
        /* 2. 원하는 애니메이션으로 jsx를 감싸준다 */
        initial={{ opacity: 0, scale:0 }}
        animate={{ opacity: 1, scale:1 }}
        exit={{ opacity: 0, scale:0}}
        transition={{ duration: 0.5 }}
             >
    <form onSubmit={onSubmit} className='joinWrap'>
    <div className="loginBtnWrap">
            <h1 className='fs20 mb30 center'>회원정보수정</h1>
            
        </div>
     
        <div className="input_area">
           
            <input onBlur={() => checkid()} type="text" placeholder="id" ref={idRef}  defaultValue={userData.id}/>
        </div>
        <div className="input_area">
           
           <input type="text" placeholder="password" ref={passwordRef} required/>
       </div>
        <div className="input_area">
          
          <input type="text" placeholder="name" ref={nameRef}  defaultValue={userData.name}/>
        </div>
        <div className="input_area">
        
            <input type="text" placeholder="phone" ref={phoneRef}  defaultValue={userData.phone} />
        </div>

        <div className="input_area">
        
        <input type="text" placeholder={userData.address}     disabled/>
        </div>
        <Address onDataChange={handleDataChange} address1={address1} address2={address2}/>
        
        <button
        style={
            {opacity: isLoading ? 0.3 : 1}
        } className='joinBtn'
        >{isLoading ? "Saving..." : "update"}</button>
     </form>
     </motion.div>
      )
}