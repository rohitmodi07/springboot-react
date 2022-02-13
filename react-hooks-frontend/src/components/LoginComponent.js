import React from 'react'
import { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService'

const LoginComponent = () => {

    const [employeeEmailId, setEmployeeEmailId] = useState('');
    const [employeeId, setEmployeeId] = useState('');
    const history = useHistory();

    const loginToPortal = (e) => {
        e.preventDefault();
        
        if(employeeEmailId === ''){
            alert("Employee email id is mandatory !");
            return false
         }else{
            const mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if(!employeeEmailId.match(mailformat)){
                alert("please enter valid email id !");
                return false
            }
         }
         if(employeeId === ''){
            alert("Employee Id is mandatory !");
            return false
         }
         
        EmployeeService.loginService(employeeId, employeeEmailId).then((response) =>{
            if(response.data!==''){
                let path = `/apply-resignation/${employeeId}/${employeeEmailId}`;
                history.push(path);
            }else{
                alert("employee does not exist!")
                return false;
            }
        }).catch(error => {
            console.log(error)
            alert("employee does not exist!")
            return false;
        })

        
    }

    const resetLogin = (e) => {
        e.preventDefault();
        if(employeeEmailId!==''){
           setEmployeeEmailId('');
        }
        if(employeeId!==''){
           setEmployeeId('');
        }
        
    }

    useEffect(() => {
        setEmployeeId('')
        setEmployeeEmailId('')
    }, [])

    return (
        <div>
        <br /><br />
        <div className = "container">
             <div className = "row">
                 <div className = "card col-md-6 offset-md-3 offset-md-3">
                     <div className = "card-body">
                         <form>
                             <div className = "form-group mb-2">
                                <label className = "form-label"> Employee Email Id :</label>
                                <input
                                    type = "text"
                                    placeholder = "Enter employee email id"
                                    name = "employeeEmailId"
                                    className = "form-control"
                                    value = {employeeEmailId}
                                    onChange = {(e) => setEmployeeEmailId(e.target.value)}
                                >
                                </input>
                             </div>
                             <div className = "form-group mb-2">
                                <label className = "form-label"> Employee Id :</label>
                                <input
                                    type = "text"
                                    placeholder = "Enter employee id"
                                    name = "employeeId"
                                    className = "form-control"
                                    value = {employeeId}
                                    onChange = {(e) => setEmployeeId(e.target.value)}
                                >
                                </input>
                             </div>
                             <div>
                               <button className = "btn btn-success" 
                                 onClick = {(e) => loginToPortal(e)} >
                                     Login 
                               </button>&nbsp;&nbsp;
                               <button className = "btn btn-success" 
                                 onClick = {(e) => resetLogin(e)} >
                                     Reset 
                               </button>
                             </div>
                             
                         </form>

                     </div>
                 </div>
             </div>

        </div>

     </div>
    )
}

export default LoginComponent
