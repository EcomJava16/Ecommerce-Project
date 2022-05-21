import { fetchProductsPending,fetchProductsError,fetchProductsSuccess } from "./productActions";

function fetchProducts() {
    return dispatch => {
        dispatch(fetchProductsPending());
        fetch("http://localhost:8080/api/v1/product")
        .then(res => res.json())
        .then(res => {
            if(res.hasErrors) {
                throw(res.error);
            }
            console.log("fetch in fetchProducts.js is called!");
            dispatch(fetchProductsSuccess(res.content));
            return res.content;
        })
        .catch(error => {
            dispatch(fetchProductsError(error));
        })
    }
}

export default fetchProducts;