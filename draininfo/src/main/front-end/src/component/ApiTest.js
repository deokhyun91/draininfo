
import axios from 'axios';
import React, {useEffect, useState} from 'react';
export default function ApiTest(){

    const [hello, setHello] = useState('sss')

    useEffect(() => {
        axios.get('/api/hello')
        .then(response => setHello(response.data))
        .catch(error => console.log(error))
    }, []);

    return(

  
       <div>
            메인페이지 백엔드에서 가져온 데이터입니다 갱신 : {hello}
        </div>
      )
}