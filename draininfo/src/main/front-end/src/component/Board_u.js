import { motion } from "framer-motion";

import { Link, useLocation } from "react-router-dom";

import { useRef, useState } from "react";
import userHook from '../hook/userhook';
import boardHook from '../hook/boardhook';
export default function Board_u(){

  
    const boardData = boardHook();
    const location = useLocation();
    const itemIndex = location.state.itemIndex;

    const [isLoading, setIsLoading] = useState(false);
    const [checked, setChecked] = useState(false); // 체크 여부 판단
    let [inputCount, setInputCount] = useState(0); // 글자수 체크
    const userData = userHook();

    const nameRef = useRef(null);
    const titleRef = useRef(null);
    const contentRef = useRef(null);

    const checkHandled = ({target}) => {
        setChecked(!checked);
    }    
    const onInputHandler = (e) => {
        setInputCount(e.target.value.length);
    };
    function onSubmit(e){
        e.preventDefault();
     
        if(!isLoading){
         
            setIsLoading(true);
       
        fetch('/board/update',{
            method:"PUT",
            headers:{
                "Content-Type":"application/json"
            },
            body: JSON.stringify({
                num : itemIndex + 1,
                name : nameRef.current.value,
                user_id : userData.id,
                title : titleRef.current.value,
                content : contentRef.current.value
                
            }),
        }).then(res => {
            if(res.ok){
                alert("수정이 완료 되었습니다.");
                window.location.replace("/board");
                setIsLoading(false);
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
             initial={{ opacity: 0, x: 20 }}
        animate={{ opacity: 1, x: 0 }}
        exit={{ opacity: 0, x: -20 }}
        transition={{ duration: 0.5 }}
             >
    {boardData.map((item, i) => (
    <>
      {itemIndex === i && (
    <form onSubmit={onSubmit} className='joinWrap wrap_in'>
        <div className="loginBtnWrap">
            <h1 className='fs20 mb30 center'><Link to="/login" className="loginH pageOn" >게시판 글 작성</Link></h1>
           
        </div>
    
        <p className="fs15 mb10 gray">*닉네임을 사용 체크 안할 시 아이디로 작성</p>
     
      
        <div className="input_area nicName">
           <labe>닉네임 사용</labe>
           <input type="checkbox" checked={checked} onChange={(e) => checkHandled(e)}/>
        </div>
        <div className="input_area">
           
           <input type="text" placeholder="닉네임" ref={nameRef} defaultValue={item.name}  disabled={!checked}/>
        </div>
        <div className="input_area">
           
           <input type="text" placeholder="제목"  ref={titleRef} defaultValue={item.title} />
       </div>
       <div className="input_area">
            <span className="fs15 checknum">현재 글자 수 : {inputCount}</span>
           <textarea onChange={onInputHandler} placeholder="내용을 입력해 주세요(200자이하)" defaultValue={item.content} maxLength="200"  ref={contentRef}  />
       </div>
        <button
        style={
            {opacity: isLoading ? 0.3 : 1}
        } className='joinBtn'
        >{isLoading ? "Saving..." : "수정완료"}</button>
     </form>)}
     </>
            ))}
    </motion.div>
      )
}