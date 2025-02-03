import { motion } from "framer-motion";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
export default function Main(){
    

    
    const history = useNavigate();
      
    const typeList = ["address", "name"];
    const [maintype, setmainType] = useState("address");
    const [mainsearchName, setmainSearchName] = useState("금정구");


    const onSubmit = async () => {  
        
        history(`/infoboard/${maintype}/all/${mainsearchName}/0`)
    
        
   
        
      };

    return(
    <motion.div
        /* 2. 원하는 애니메이션으로 jsx를 감싸준다 */
        initial={{ opacity: 0, scale:0 }}
        animate={{ opacity: 1, scale:1 }}
        exit={{ opacity: 0, scale:0}}
        transition={{ duration: 0.5 }}
             >
    <div className="wrap">
        <div className="mainSearch subSearch mt30">
        <select onChange={(e) => {
            setmainType(e.target.value);}}>
            {typeList.map((item) => (
            <option value={item} key={item}>
              {item}
            </option>
            ))}
                
            </select>
            <input  onChange={(e) => {
             setmainSearchName(e.target.value);
             }} type="search" className="search" placeholder="집 주변 배출시설을 검색해보세요"/>
            <button onClick={onSubmit} className="searchBtn" type="button">검색</button>
         
    
        
        </div>
       
    </div>
    </motion.div>
      )
}