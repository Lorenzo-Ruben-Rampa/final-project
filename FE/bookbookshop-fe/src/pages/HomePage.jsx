import axios from "axios";
import { useContext, useEffect, useState } from "react";
import BookCard from "../components/BookCard";
import GlobalContext from "../contexts/GlobalContext";

const Homepage = () => {

    const [books, setBooks] = useState([]);
    const { setIsLoading } = useContext(GlobalContext);

    const fetchBooks = () => {
        console.log("Fetching books...");
        setIsLoading(true);
        axios.get(import.meta.env.VITE_API_URL)
            .then((response) => {
                console.log(response.data);
                const {data } = response;
                setBooks(data);
            })
            .catch((error) => {console.error(error) })
            .then(() => {
                setIsLoading(false);
            })
    }

    useEffect(() => {
    fetchBooks();
    }, [])
    
    return (
        <>
            <section>
                {
                    books.map((book) => 
                        <BookCard key={book.id} book={book} isDetail={false} />)
                }
            </section>
        </>
    )
}

export default Homepage