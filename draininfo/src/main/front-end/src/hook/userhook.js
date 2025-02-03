import { useEffect, useState } from "react";
import axios from "axios";

export default function useFetch(){
  
    const [userData, setUserData] = useState("");
   
    const onSubmit3 = async () => {  
     
        
        axios.get('/principal')
        .then((response) => {
           
           
            setUserData(response.data.data)
            

        }).catch(error => {
            console.log(error);
            setUserData("error")
        })
        
      };
    
      useEffect(() => {
        
       onSubmit3();
            
   
        
      },[]);
      
    return userData;
}