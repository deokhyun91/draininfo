import { Link } from "react-router-dom";
import { FaUserCircle } from "react-icons/fa";
import { FaSignOutAlt } from "react-icons/fa";
import imgLogo from '../img/logo.png';
import userHook from '../hook/userhook';
export default function Header(){
 
    const userData = userHook();
    function logout(){
        window.location.replace("/logout");
    }

    return(

        <div className="head">
            
            <ul className="h_menu">
                <li>
                    <Link to="/"><img src={imgLogo} className="logo"/></Link>
                </li>
                <li>
                    <Link to="/infoboard">환경오염 배출시설</Link>
                </li>
                <li>
                    <Link to="/board">게시판</Link>
                </li>
            </ul>
            <ul className="h_menu">
                
              
                <li className="user_wrap">
                    {userData.id === undefined ? <></> :   <button type="button" className="logoutBtn" onClick={logout}><FaSignOutAlt /></button>}
                  
                    <Link to={userData.id === undefined ? "/login" : "/mypage"}><FaUserCircle /> <span >{userData.id}</span></Link>
                </li>
            </ul>
        </div>  
    
      )
}