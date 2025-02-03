
import './App.css';



import { BrowserRouter , Route, Routes } from 'react-router-dom';
import ApiTest from './component/ApiTest';
import Join from './component/Join';
import Header from './component/Header';
import Main from './component/Main';
import { AnimatePresence } from 'framer-motion';
import InfoBoard from './component/InfoBoard';
import Board from './component/Board';
import Login from './component/login';
import Board_V from './component/Board_V';
import Board_W from './component/Board_w';
import Mypage from './component/Mypage';
import UserUpdate from './component/UserUpdate';
import Board_u from './component/Board_u';
import MyBoard from './component/MyBoard';


function App() {
 
    //현재 페이지의 경로를 나타내는 속성
    const path = window.location.pathname;
    return (
    <BrowserRouter>
        <AnimatePresence>
        <Header />
        <Routes>
         
            <Route path='/test' element={<ApiTest />}/>
            <Route exact path='/' element={<Main />} />
            <Route path='/infoboard' element={<InfoBoard />}/>
            <Route path='/infoboard/:maintype/:sort/:mainsearchName/:index' element={<InfoBoard />}/>
            <Route path='/board' element={<Board />}/>
            <Route path='/myboard' element={<MyBoard />}/>
            <Route path='/board_v' element={<Board_V />}/>
            <Route path='/board_w' element={<Board_W />}/>
            <Route path='/board_update' element={<Board_u />}/>
            <Route path='/join' element={<Join />}/>
            <Route path='/login' element={<Login />}/>
            <Route path='/mypage' element={<Mypage   />}/>
            <Route path='/userUpdate' element={<UserUpdate />}/>
        

            <Route path='/api' element={<ApiTest />}/>
        </Routes>
        </AnimatePresence>
    
    </BrowserRouter>
    );
}

export default App;
