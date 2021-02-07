import React, { Component } from "react";
import MovieService from "../services/MovieService";

const apiEndpoint = "http://localhost:8080/api/search";

class NewtonTable extends Component {
    state = {
        result: []
    }


    async handleServiceCall() {
        const  {data: result} = await MovieService.get(apiEndpoint);
        this.setState({result})
    }
    

    render() {
        const { length: resultSize} = this.state.result;
        const {result} = this.state;
        if(resultSize === 0) return <p> You have not retrieved the Newton Movies from the service <button onClick={() => this.handleServiceCall()}>Call Service</button></p>

        return (
            <div className="container">
                <table className="table table-bordered">
                    <thead className="thead-dark">
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Year</th>
                            <th scope="col">Link to IMDB</th>
                        </tr>
                    </thead>
                    <tbody>
                        {result.map((item, index) => (
                            <tr key={index}>
                                    <td key={index+item.title}>{item.Title}</td>
                                    <td key={index+item.Year}>{item.Year}</td>
                                    <td key={index+item.imdbID}><a href={`https://www.imdb.com/title/`+item.imdbID}  target="_blank">Link to IMDB</a></td>
                            </tr>
                        ))}     
                    </tbody>
                </table>
                
            </div>
        );

        
    }
}


export default NewtonTable;