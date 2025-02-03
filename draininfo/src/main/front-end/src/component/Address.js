
import { useState } from 'react';
import { useDaumPostcodePopup } from 'react-daum-postcode'; // Daum 주소 검색 관련 hook
//주소 api

// const DaumPost = ({ setAddress }) 
const DaumPost = ({onDataChange, address1, address2}) => {
    
    const postcodeScriptUrl = 'https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js';
    const open = useDaumPostcodePopup(postcodeScriptUrl);

    const handleComplete = (data) => {
        let fullAddress = data.address;
        let extraAddress = ''; //추가될 주소
        let localAddress = data.sido + ' ' + data.sigungu; //지역주소(시, 도 + 시, 군, 구)

        if (data.addressType === 'R') {//주소타입이 도로명주소일 경우
            if (data.bname !== '') {
                extraAddress += data.bname; //법정동, 법정리
            }
            if (data.buildingName !== '') {//건물명
                extraAddress += (extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName);
            }
            //지역주소 제외 전체주소 치환
            fullAddress = fullAddress.replace(localAddress, '');
            //조건 판단 완료 후 지역 주소 및 상세주소 state 수정
            fullAddress += (extraAddress !== '' ? ` (${extraAddress})` : '');
        }
       
            onDataChange(localAddress,fullAddress); 
      
      
        // setAddress(fullAddress); // setAddress를 호출하여 부모 컴포넌트의 상태를 업데이트
    };

    const handleClick = () => {
        open({ onComplete: handleComplete });
    };

    return(
        <>
            <div  type="button" className="addressBtn" onClick={handleClick}>주소검색</div>
            <div className="input_area">
        
                <input type="text" placeholder={address1} value={address1} disabled />
            </div>
            <div className="input_area">
                
                <input type="text" placeholder={address2} value={address2} disabled />
            </div>
        </>

    ) 
    
    
};

export default DaumPost;