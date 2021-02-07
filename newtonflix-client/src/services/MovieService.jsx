import axios from "axios";


axios.interceptors.response.use(null, error => {
  const expectedError = 
  error.response && 
  error.response.status >= 400 &&
  error.response.status > 500;

  if(!expectedError){
    console.log("Logging unexcpected error", error);
    alert("An unexpected error occured");
  }
  return Promise.reject(error);
})

export default {
    get: axios.get
};