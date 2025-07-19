import axios from "axios";
import { useContext } from "react"
import { useState, useEffect } from "react"
import GlobalContext from "../contexts/GlobalContext"
import BookCard from "../components/BookCard"

const BookPage = () => {
    const {book, setbook} = useState({});
    const {setIsLoading} = useContext(GlobalContext);

    const fetchBook = () => {
        setIsLoading(true)
        axios.get(`${import.meta.env.VITE_API_URL}/${id}`)
        .then(response => {
            const {data} = response
            setBook(data)
        })
        .catch(error => {console.log(error) })
        .then (() => {setIsLoading(false) })
    }
    useEffect(fetchBook, [])
    
    return (
        <>
            <h1>{book.name}</h1>
            {book.id && <BookCard book={book} isDetail={true} /> }
        </>
    )
}

export default BookPage