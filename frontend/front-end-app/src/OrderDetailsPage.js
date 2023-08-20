import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux';

function OrderDetailsPage() {
    const emailid = useSelector(state => state.email);
    const [orderDetails, setOrderDetails] = useState([]);

    useEffect(() => {
        async function fetchOrderDetails() {
            try {
                const response = await axios.post("http://localhost:8080/orders/viewOrderDetails2", {
                    email: emailid
        
                    
                });
                setOrderDetails(response.data);
            } catch (error) {
                console.error('Error fetching order details:', error);
            }
        }

        fetchOrderDetails();
    }, []);

    return (
        <div>
            <h2>Order History</h2>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Placed</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Account Number</th>
                        <th>Email</th>
                        
                    </tr>
                </thead>
                <tbody>
                    {orderDetails.map(order => (
                        <tr key={order.orderid}>
                            <td>{order.orderid}</td>
                            <td>{order.orderplaced}</td>
                            <td>{order.product.pname}</td>
                            <td>{order.product.price}</td>
                            <td>{order.account.accno}</td>
                            <td>{order.account.email}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default OrderDetailsPage;
