
import React, { Component } from "react";
import './App.css';
import NewtonTable from './components/NewtonTable';

class App extends Component {
  render() {
    return(
      <React.Fragment>
        <NewtonTable></NewtonTable>
      </React.Fragment>
    );
  }
}

export default App;
