import { useEffect, useState } from "react";
import axios from "axios";

export default function Boardhook(){
  
    const [boardData, setBoardData] = useState([]);
   
    const onSubmit3 = async () => {  
     
        
        axios.get('/boarddata')
        .then((response) => {
           
           
            setBoardData(response.data.data)
            

        }).catch(error => {
            console.log(error);
            setBoardData("error")
        })
        
      };
    
      useEffect(() => {
        
       onSubmit3();
            
   
        
      },[]);
      
    return boardData;
}