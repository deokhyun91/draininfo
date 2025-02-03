import { BiSolidUserPin } from "react-icons/bi";
import { Link, Navigate } from "react-router-dom";
import userHook from '../hook/userhook';
import { useEffect, useState } from "react";

export default function Mypage(){

    
    
    const userData = userHook();

 
    function deleteUser(){
        if (!window.confirm(userData.id + "님 회원탈퇴 하시겠습니까?")) {
            alert("취소(아니오)를 누르셨습니다.");
        }else{
            fetch(`/delete/userid?id=` + userData.id,{
                method:"DELETE"
            }).then(res => {
                if(res.ok){
                    alert("탈퇴되었습니다.");
                    
                    window.location.replace("/logout");
                    
                    return res.json();
                }else{
                   
                    return res.json();
                   
                  
                }
            }).then((jsons) => {
                console.log(jsons)
                var err = jsons.message;
              
                alert(err.replace(/signUp.arg0./g,""));
              });
        }
        
        
    }
   
    if(userData === "error"){
     
        alert("회원만 접근 가능합니다");
        return (<Navigate to={'/login'} replace />);
   
    
    }else {
            return(
                <>
                {
    
               
                <div className="joinWrap">
                    <div className="myPage">
                        <h1 className='fs20 mb30 center '><BiSolidUserPin className="fs42"/> <span className="vMiddle">마이페이지</span></h1>
                        <ul className="myPageInfo">
                            <li ><strong>아이디:</strong> {userData.id}</li>
                            <li ><strong>이름:</strong> {userData.name}</li>
                            <li ><strong>연락처:</strong> {userData.phone}</li>
                            <li ><strong>주소:</strong> {userData.address}</li>
                            
                        </ul>
                        <Link to="/myboard" className="myWriteBtn">나의 게시글</Link>
                        <Link to="/userUpdate" className="updateBtn">회원정보수정</Link>
                        <button type="button"  className="deleteBtn" onClick={deleteUser}>회원탈퇴</button>
                    </div>
                   
                </div>  
                }
                </>
              )
         
        }
    }
    
    
  


