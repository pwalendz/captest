import { Link, useLocation, Outlet } from 'react-router-dom';
import './App.css'; // Import your CSS file

function Customer() {
    return (
        <div>
            <h2>Welcome to Customer Home Page</h2>
            <Link className="nav-link" to="viewMedicine">View Medicine </Link>
            <Link className="nav-link" to="order-details">Order Details </Link>
            <Link className="nav-link" to="/">Logout</Link>
            <hr />
            <Outlet></Outlet>
        </div>
    );
}

export default Customer;
