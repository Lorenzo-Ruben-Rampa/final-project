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
                console.log(response);
                console.log(books);
                const {data } = response;
                setBooks(data);
                console.log(books);
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
            <h1 className="text-primary">Bibliotecarium</h1>
            <section>
                <h2><i>Verba volant, scripta manent</i></h2>
                {
                    books.map((book) => 
                        <BookCard key={book.id} book={book} isDetail={false} />)
                }
            </section>
        </>
    )
}

export default Homepage