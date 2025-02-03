import { motion } from "framer-motion";
import { FaCaretRight } from "react-icons/fa";
import { FaCaretLeft } from "react-icons/fa";
import { FaPen } from "react-icons/fa";
import { Link } from "react-router-dom";
import userHook from '../hook/userhook';
import boardHook from '../hook/boardhook';
import { useEffect, useState } from "react";
export default function MyBoard(){
    const userData = userHook();
    const boardData = boardHook();


    const [total, setTotal] = useState(0);//데이터 총개수
    const itemNum = Math.floor(total / 10);//총 페이지 개수
    const [pageNum, setPageNum] = useState(0);//보여줄 페이지 개수
    const [index, setIndex] = useState(0); //현재 페이지

    const [start, setStart] = useState(1);
    const noPrev = start === 1; // 이전 페이지가 없는 경우
    const noNext = start + pageNum -1 >= itemNum// 다음 페이지가 없는 경우
    useEffect(() => {
        
        setTotal(boardData.length);
        setPageNum(10);
    },[boardData]);
 


    const changeIndex = (i) => { 
        setIndex(i); 
        }
    const changeBackStart = () => { 
        setStart((prev) => prev + 10);
         
        }
    const changePrevStart = () => { 
        setStart((prev) => prev - 10); 
        
    }
    const handleClick = () => {
        window.scrollTo({
          top: 0,
          behavior: 'smooth',
        });
      };
    let indexStart = index * 10;
    let indexEnd = index * 10 + 10;

  
    return(
     <motion.div
        /* 2. 원하는 애니메이션으로 jsx를 감싸준다 */
             initial={{ opacity: 0, x: 20 }}
        animate={{ opacity: 1, x: 0 }}
        exit={{ opacity: 0, x: -20 }}
        transition={{ duration: 0.5 }}
             >
    <div className="wrapSub">
      
        <div className="subBoard subBoard2 mt30">
            <ul className="tableHead">
                <li>No</li>
                <li>제목</li>
                <li>글쓴이</li>
                <li>날짜</li>
            </ul>
            {boardData.map((item, i) => (
                <>
                {userData.id === item.user_id && indexStart <= i && i < indexEnd? 
                <div className="tableBodyWrap" >
                <ul className="tableBody">
                    <Link to="/board_v" state={{ itemIndex: i }}>
                        <li>{item.num}</li>
                        <li>{item.title}</li>
                        <li>{item.name === "" ? item.user_id : item.name}</li>
                        <li>{item.update_day}</li>
                    </Link>
                </ul>
                
                </div>

                :<></>}
                </>
            ))}
         
          
        </div>
        <ul className="listNum mt30 mb30">
            <li onClick={() => {
              changePrevStart();
              
            }} className={noPrev ? "invisible" : ""}><FaCaretLeft className="mt5"/></li>
            {[...Array(pageNum)].map((a, i) => (
                <>
                {start + i <= itemNum + 1 && (
                    <li onClick={() => {
                        changeIndex(start+i-1);
                        handleClick();
                    }}>{start + i}</li>
                )}
                </>
            ))}
          
            
            <li onClick={() => {
              changeBackStart();
          
            }} className={noNext ? "invisible" : ""}><FaCaretRight className="mt5"/></li>
        </ul>
        {
           userData === "error" ? <></> : <Link to="/board_w" className=" writeBtn"><FaPen /></Link> 
        }
        
    </div>
    </motion.div>
      )
}