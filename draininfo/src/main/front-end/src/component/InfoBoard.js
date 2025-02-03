import { motion } from "framer-motion";
import { FcFactory } from "react-icons/fc";
import { FaCaretRight } from "react-icons/fa";
import { FaCaretLeft } from "react-icons/fa";
import { useEffect, useRef, useState } from "react";
import axios from "axios";
import { useLocation, useParams } from "react-router-dom";
import useDidMountEffect from "../hook/useDidMountEffect";
const{kakao} = window;
export default function InfoBoard(){

    const [isOnSubmit, setIsOnSubmit] = useState(false);
    const [isLoading, setIsLoading] = useState(false);
    const [isMounted, setIsMounted] = useState(false);
    const [isMap, setIsMap] = useState(false);
 
    const typeList = ["address", "name"];
    const sortList = ["all"];
    const [type2, setType2] = useState("all");
    const [type, setType] = useState("");
    const [sort, setSort] = useState("all");
    const [searchName, setSearchName] = useState("금정구");
  
    const [officeName, setOfficeName] = useState();
    const [lng, setLng] = useState();
    const [lat, setLat] = useState();
    const [apiDataList, setApiDataList] = useState([]);

   
    const [total, setTotal] = useState(0);//데이터 총개수
    const itemNum = Math.floor(total / 20);//총 페이지 개수
    const [pageNum, setPageNum] = useState(0);//보여줄 페이지 개수
    const [index, setIndex] = useState(0); //현재 페이지

    const [start, setStart] = useState(1);
    const noPrev = start === 1; // 이전 페이지가 없는 경우
    const noNext = start + pageNum -1 >= itemNum// 다음 페이지가 없는 경우

    // 메인페이지에서 값받기
    const { maintype, mainsearchName } = useParams() ;
    useEffect(() => {
        if(maintype !== undefined){
           
            setType(maintype);
            setSearchName(mainsearchName);
            setIsOnSubmit(false);
        }
        
    },[]);
    
         
      
    const handleClick = () => {
        window.scrollTo({
          top: 0,
          behavior: 'smooth',
        });
      };


    const onSubmit = async () => {  
        if(!isLoading){
            setIsLoading(true);
        // window.location.href = "/infoboard?type=" + type + "&sort="+sort+"&searchName="+searchName+"&index="+index;
        axios.get('/api/search?type='+type+"&sort="+sort+"&searchName="+searchName+"&index="+index)
        .then((response) => {
            if(response.data.length === 0){
               console.log("검색결과없음");
               
               setTotal(0);
               setPageNum(0);
               setApiDataList([] );
               setIsLoading(false);
               setIsOnSubmit(false);
               
            }
            
            setApiDataList(response.data );
            setTotal(response.data[0].size );
            setPageNum(10);
            setIsLoading(false);
            setIsOnSubmit(false);
           

        }).catch(error => console.log(error))
        }
      };

    const changeIndex = (i) => { 
        setIndex(i); 
        }
    const changeBackStart = () => { 
        setStart((prev) => prev + 10);
         
        }
    const changePrevStart = () => { 
        setStart((prev) => prev - 10); 
        
    }




      useEffect(() => {
        if(isMounted){
        
            onSubmit();
            

          
        }else{
            setIsMounted(true);
        }
        
      },[index,start,isOnSubmit,type]);

      
    // 마커의 위치로 지도의 중심 좌표 이동하기
      const moveMap = (name,lng,lat) => { 
        setOfficeName(name);
        setLng(lng);
        setLat(lat);
        setIsMap(true);
        
    }
   
  

      useEffect(() => {
        if(isMounted){
          
            //지도
            // 이미지 지도에 표시할 마커입니다
            
            var marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(lat, lng), 
                title : officeName,
               
            });
            const staticMapContainer  = document.querySelector('.subMap'), // 이미지 지도를 표시할 div
                staticMapOption = { 
                    center: new kakao.maps.LatLng(lat, lng), // 이미지 지도의 중심좌표
                    level: 3, // 이미지 지도의 확대 레벨
                   
                    
                };

            // 이미지 지도를 생성합니다
            const staticMap = new kakao.maps.Map(staticMapContainer, staticMapOption);
            marker.setMap(staticMap);
            
             
           
            }else{
                setIsMounted(true);
            }
       
        
      },[officeName]);

     
      
      const activeEnter = (e) => {
        if(e.key === "Enter") {
            setType("address");
            setIsOnSubmit(true);
           console.log("엔터키")
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
    <div className="wrapSub">
        <div className="subMap mt30"></div>

        <div className="subSearch mt30">
            <select onChange={(e) => {
            setType(e.target.value);}}>
            {typeList.map((item) => (
            <option value={item} key={item}>
              {item}
            </option>
            ))}
                
            </select>
            <input  onChange={(e) => {
             setSearchName(e.target.value);
             }}  onKeyDown={(e) => activeEnter(e)} type="search" className="search" placeholder="집 주변 배출시설을 검색해보세요"/>
            <button style={
            {opacity: isLoading ? 0.3 : 1}
            } onClick={() => {
                setType("address");
                setIsOnSubmit(true);
            }} className="searchBtn" type="button">{isLoading ? "Saving..." : "검색"}</button>
            <button style={
            {opacity: isLoading ? 0.3 : 1}
            } onClick={() => {
                setType("all");
                setIsOnSubmit(true);
            }} className="searchAllBtn" type="button">{isLoading ? "Saving..." : "전체보기"}</button>
             <select onChange={(e) => {
            setSort(e.target.value);}}>
            {sortList.map((item) => (
            <option value={item} key={item}>
              {item}
            </option>
            ))}
                
            </select>
        
        </div>
        <div className="subBoard ">
            <div className={apiDataList.length === 0 ? "visible" : "invisible"}>검색 결과가 없습니다.</div>
            {apiDataList.map((item) => (
                <div className="box1" onClick={() => {
                    moveMap(item.office_nm,item.lng,item.lat);
                  
                    handleClick();
                  }}>
                    <div className="box1_1">
                        <FcFactory className=" box1_1_1"/>
                        <ul className="box1_1_2">
                            <li className="fwb mb10">{item.office_nm}</li>
                            <li className="fs15">{item.gugun}</li>
                            <li className="fs15">{item.office_type} </li>
                        </ul>
                    </div>
                    
                    <ul className="box1_2">
                        <li className="fs15">Tel. {item.tel}</li>
                        <li className="fs15 ml20">Address. {item.road_nm}</li>
                    </ul>
                
                </div>
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
    </div>
    </motion.div>
      )
}