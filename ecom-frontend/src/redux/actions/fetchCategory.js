import { fetchCategoryError,fetchCategoryPending, fetchCategorySuccess } from "./categoryActions";

function fetchCategory(){
    return dispatch => {
        dispatch(fetchCategoryPending());
        fetch("http://localhost:8080/api/v1/category")
        .then(res => res.json())
        .then(res => {
            if(res.hasErrors) {
                throw(res.error);
            }
            console.log("fetch in fetchCategory.js is called!");
            dispatch(fetchCategorySuccess(res.content));
            return res.content;
        })
        .catch(error => {
            dispatch(fetchCategoryError(error));
        })
    }
}

export default fetchCategory;