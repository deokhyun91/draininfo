
import axios from "axios";
import { motion } from "framer-motion";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
export default function Login(){
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const [user, setUser] = useState({
        username: '',
        password: '',
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUser({ ...user, [name]: value });
    };
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
          const formData = new FormData();
          formData.append('username', user.username);
          formData.append('password', user.password);
    
          const response = await axios({
            url: '/user/login',
            method: 'POST',
            data: formData,
            withCredentials: true,
          });
          if (response.status === 200) {
            alert('로그인 성공! ');
            console.log(response);
            
            window.location.replace("/")
          }
        } catch (error) {
            alert('아이디 비밀번호가 틀립니다.');
          console.log('로그인 에러: ', error);
        } };
    return(
    
        <motion.div
        /* 2. 원하는 애니메이션으로 jsx를 감싸준다 */
        initial={{ opacity: 0, scale:0 }}
        animate={{ opacity: 1, scale:1 }}
        exit={{ opacity: 0, scale:0}}
        transition={{ duration: 0.5 }}
             >
    <form onSubmit={handleSubmit} className='joinWrap'>
        <div className="loginBtnWrap">
            <h1 className='fs20 mb30 center'><Link to="/login" className="loginH pageOn" >로그인</Link></h1>
            <h1 className='fs20 mb30 center'><Link to="/join" className="joinH">회원가입</Link></h1>
        </div>
    
     
        <div className="input_area">
           
            <input type="text" name="username" placeholder="id"  value={user.username} onChange={handleChange} required/>
        </div>
        <div className="input_area">
           
           <input type="password" name="password" placeholder="password" value={user.password} onChange={handleChange} required/>
       </div>

       
        <button type="submit"
        style={
            {opacity: isLoading ? 0.3 : 1}
        } className='joinBtn'
        >{isLoading ? "Saving..." : "Login"}</button>
     </form>
     </motion.div>
      )
}