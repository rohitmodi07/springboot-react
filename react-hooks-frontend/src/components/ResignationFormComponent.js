import React, {useState, useEffect} from 'react'
import {Link, useParams } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService'

const ResignationFormComponent = () => {

    const [employeeEmailId, setEmployeeEmailId] = useState('')
    const [employeeId, setEmployeeId] = useState('')
    const [level, setLevel] = useState('')
    const [designation, setDesignation] = useState('')
    const [dateOfJoining, setDateOfJoining] = useState('')
    const [dateOfResignation, setDateOfResignation] = useState('')
    const [lastWorkingDay, setLastWorkingDay] = useState('')
    const [resReason, setResReason] = useState('')
    const [buttonText, setButtonText] = useState('Submit Resignation')
    const {empId, empEmail} = useParams();
    

    const saveResigantion = (e) => {
        e.preventDefault();
        const employee = {employeeEmailId, employeeId, level, designation, dateOfJoining, dateOfResignation,
                          lastWorkingDay, resReason}
        
        if(buttonText === "Submit Resignation"){
            
            EmployeeService.saveResignation(empId, employee).then((response) => {
                setEmployeeEmailId(response.data.employeeEmailId)
                setEmployeeId(response.data.employeeId)
                setLevel(response.data.level)
                setDesignation(response.data.designation)
                setDateOfJoining(response.data.dateOfJoining)
                setDateOfResignation(response.data.dateOfResignation)
                setLastWorkingDay(response.data.lastWorkingDay)
                setButtonText("Withdraw Resignation")
            }).catch(error => {
                console.log(error)
            })
        }else{
            if(resReason !== ''){
                alert(" please remove resignation reason ! ")
                return false;
            }
            EmployeeService.saveResignation(empId, employee).then((response) => {
                setEmployeeEmailId(response.data.employeeEmailId)
                setEmployeeId(response.data.employeeId)
                setLevel(response.data.level)
                setDesignation(response.data.designation)
                setDateOfJoining(response.data.dateOfJoining)
                setDateOfResignation(response.data.dateOfResignation)
                setLastWorkingDay(response.data.lastWorkingDay)
                setButtonText("Submit Resignation")
            }).catch(error => {
                console.log(error)
            })
        }                
        
    }

    useEffect(() => {

        EmployeeService.loginService(empId, empEmail).then((response) =>{
            setEmployeeEmailId(response.data.employeeEmailId)
            setEmployeeId(response.data.employeeId)
            setLevel(response.data.level)
            setDesignation(response.data.designation)
            setDateOfJoining(response.data.dateOfJoining)
            setDateOfResignation(response.data.dateOfResignation)
            setLastWorkingDay(response.data.lastWorkingDay)
            if(response.data.lastWorkingDay!==''){
               setButtonText('Withdraw Resignation')
            }
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {
          return <h3 className = "text-center">Employee Information</h3>
    }
    const titleResignation = () => {
        return <h3 className = "text-center">Resignation Information</h3>
    }

    return (
        <div>
           <br />
           <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                       {
                           title()
                       }
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Employee Email : </label>  {employeeEmailId}
                                    <br/>
                                    <label className = "form-label"> Employee Id : </label>  {employeeId}
                                    <br/>
                                    <label className = "form-label"> Level : </label>  {level}
                                    <br/>
                                    <label className = "form-label"> Designation : </label>  {designation}
                                    <br/>
                                    <label className = "form-label"> Date of joining : </label>  {dateOfJoining}
                                    
                                </div>

                            </form>

                        </div>
                    </div>
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                       {
                           titleResignation()
                       }
                        <div className = "card-body">
                            <form>
                                <div className = "form-group mb-2">
                                    <label className = "form-label"> Date of Resignation : </label> {dateOfResignation}
                                    <br/>
                                    <label className = "form-label"> Last working day : </label> {lastWorkingDay}
                                    <br/>
                                    <label className = "form-label"> Reason : </label> 
                                    <input
                                        type = "text"
                                        placeholder = "Enter reason"
                                        name = "resReason"
                                        className = "form-control"
                                        value = {resReason}
                                        onChange = {(e) => setResReason(e.target.value)}
                                    >
                                    </input>
                                </div> 

                                <button className = "btn btn-success" onClick = {(e) => saveResigantion(e)} >{buttonText}</button>
                                &nbsp;&nbsp;<Link to="/login" className="btn btn-danger"> Cancel </Link>&nbsp;&nbsp;
                                
                            </form>

                        </div>
                    </div>
                </div>

           </div>

        </div>
    )
}

export default ResignationFormComponent
