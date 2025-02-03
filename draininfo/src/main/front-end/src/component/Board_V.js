import { motion } from "framer-motion";
import { FaCaretRight } from "react-icons/fa";
import { FaCaretLeft } from "react-icons/fa";
import { FaPen } from "react-icons/fa";
import { Link, useLocation } from "react-router-dom";
import { FaListUl } from "react-icons/fa";
import userHook from '../hook/userhook';
import boardHook from '../hook/boardhook';
export default function Board_V(){

  const userData = userHook();
  const boardData = boardHook();
  const location = useLocation();
  const itemIndex = location.state.itemIndex;
  function deleteBoard(){
    if (!window.confirm(userData.id + "님 게시글을 하시겠습니까?")) {
        alert("취소(아니오)를 누르셨습니다.");
    }else{
        fetch(`/deleteBoard/boardnum?num=` + (itemIndex + 1),{
            method:"DELETE"
        }).then(res => {
            if(res.ok){
                alert("삭제되었습니다.");
                
                window.location.replace("/board");
                
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
                {itemIndex === i && (<div className="wrapSub">
                          
                  <div className="subBoard_v mt30">
                  
                    <h1 className="mb10 fs18">{item.title}</h1>
                    <ul>
                      <li>{item.name === "" ? item.user_id : item.name}</li>
                      <li>{item.update_day}</li>
                    </ul>

                    <p className="s_content"> 
                       {item.content}
                    </p>
                  </div>

                  <ul className="subBoard_btn_wrap">
                      <li>
                      {
                      userData === "error" ? <></> : <Link to="/board_w" className=" writeBtn"><FaPen /></Link> 
                      }
                      </li>
                      <li>
                      {
                      userData.id === item.user_id ? <Link to="/board_update"  state={{ itemIndex: i }} className=" writeBtn po_initial boardUpdatebtn">수정</Link> : <></>
                      }
                     
                      </li>
                      <li>
                      {
                      userData.id === item.user_id ? <button type="button"  className="writeBtn po_initial boardDeletebtn" onClick={deleteBoard}>삭제</button> : <></>
                      }
                      </li>
                      <li>
                      <Link to="/board" className=" writeBtn po_initial"><FaListUl /></Link>
                      </li>
                  </ul>

                  </div>)}
                  </>
              ))}
   
    </motion.div>
      )
}