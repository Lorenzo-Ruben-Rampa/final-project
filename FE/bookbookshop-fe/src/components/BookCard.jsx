import PropTypes from 'prop-types'
import {Link} from "react-router-dom"

const BookCard = (props) => {
    const isDetail = props.isDetail || false;
    const {id, name, author, publisher, synopsis, coverImage, genres, publicationDate, ISBN, price} = props.book;

    const formDate = () => {
        const date = new Date(publicationDate);
        const year = date.getFullYear();
        return `${year}`;
    }

    return ( 
        <div className="card mt-5 mb-5">
            <div className="cardtitle">
                {!isDetail && <h2>{name}</h2>}
                <div className="d-flex justify-content-between align-items-center">
                    <div>
                        <address><em>Scritto da {author} nel <time>{formDate()}</time></em></address>
                    </div>              
                    <div>
                        <address><em>Edito {publisher}</em></address>
                    </div>
                 </div>
                <section className="genres mb-3">
                    {
                    genres.map((genre) => 
                        <div className="badge bg-primary me-3 {isDetail} ? 'fs-3 : ''" key={genre.id}>{genre.name}</div>)
                    }
                    </section> 
            </div>
            {isDetail ?
                 <div className="card-body">
                     <p>{synopsis}</p>
                 </div> : ""
            }
            <div className="card-footer d-flex justify-content-between align-items-center">
                {isDetail ?
                <Link to={'/'} className="btn btn-secondary">Home</Link> :
                <Link to={`/books/${id}`} className="btn btn-primary">Scopri di più</Link>}
            </div>
        </div>
    )
}

BookCard.proptypes = {
    isDetail: PropTypes.bool,
    book: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    author: PropTypes.string.isRequired,
    publisher: PropTypes.string.isRequired,
    synopsis: PropTypes.string,
    coverImage: PropTypes.string,
    genres: PropTypes.array,
    publicationDate: PropTypes.string.isRequired,
    ISBN: PropTypes.string, 
    price: PropTypes.int
    }).isRequired
}

export default BookCard