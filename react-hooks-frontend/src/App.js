import './App.css';
import {BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import ResignationFormComponent from './components/ResignationFormComponent';
import LoginComponent from './components/LoginComponent';

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div className= "container">
          <Switch>
              <Route exact path = "/" component = {LoginComponent}></Route>
              <Route path = "/login" component = {LoginComponent}></Route>
              <Route path = "/apply-resignation/:empId/:empEmail" component = {ResignationFormComponent} ></Route>
            </Switch>
        </div>
        
        </Router>
        <FooterComponent/>
    </div>
  );
}

export default App;
