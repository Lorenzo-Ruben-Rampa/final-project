import axios from "axios";
import { useContext } from "react"
import { useState, useEffect } from "react"
import GlobalContext from "../contexts/GlobalContext"
import BookCard from "../components/BookCard"
import { useParams } from "react-router-dom";

const BookPage = () => {
    //Inizializzo come null in attesa di ricevere i dati
    const [book, setBook] = useState(null);
    // Destructuring
    const {setIsLoading} = useContext(GlobalContext);

    //Prendo l'id dai parametri dell'url
    const { id } = useParams();

    const fetchBook = () => {
        setIsLoading(true)

        // Mi assicuro che l'id sia valido prima del fetch
        if (!id) {
            console.error("Book ID is missing from URL parameters.");
            setIsLoading(false);
            return;
        }

        axios.get(`${import.meta.env.VITE_API_URL}/${id}`)
        .then(response => {
            const {data} = response
            setBook(data)
        })
        .catch(error => {console.error("Errore nella ricezione dei dati", error) })
        .then (() => {setIsLoading(false) });
    };

    useEffect(() => {
        fetchBook(); 
    }, [id]);
    
    return (
        <>
            {book ? (
                <div className="grey">
                    <h1 className="grey text-warning">{book.name}</h1>
                    <BookCard book={book} isDetail={true} />
                </div>
            ) : (
                <p>Caricamento libro o libro non trovato...</p>
            )}
        </>
    )
};

export default BookPage