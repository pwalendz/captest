import React, { useState } from 'react';
import axios from 'axios';

function AddProductForm({ fetchProducts }) {
    const [showForm, setShowForm] = useState(false);
    const [newProduct, setNewProduct] = useState({
        pname: '',
        price: '',
        qty: '',
    });

    const handleAddProductClick = () => {
        setShowForm(true);
    };

    const handleAddProductSubmit = async () => {
        try {
            const response = await axios.post('http://localhost:8080/product/storeProductInfo', newProduct);
            console.log('Product added:', response.data);
            // Refresh the product list after adding
            fetchProducts();
            setShowForm(false);
            setNewProduct({
                pname: '',
                price: '',
                qty: '',
            });
        } catch (error) {
            console.error('Error adding product:', error);
        }
    };

    return (
        <div>
            <button onClick={handleAddProductClick}>Add Product</button>
            {showForm && (
                <div>
                    <h3>Add New Product</h3>
                    <input
                        type="text"
                        placeholder="Name"
                        value={newProduct.pname}
                        onChange={(e) => setNewProduct({ ...newProduct, pname: e.target.value })}
                    />
                    <input
                        type="number"
                        placeholder="Price"
                        value={newProduct.price}
                        onChange={(e) => setNewProduct({ ...newProduct, price: e.target.value })}
                    />
                    <input
                        type="number"
                        placeholder="Quantity"
                        value={newProduct.qty}
                        onChange={(e) => setNewProduct({ ...newProduct, qty: e.target.value })}
                    />
                    <button onClick={handleAddProductSubmit}>Submit Product</button>
                </div>
            )}
        </div>
    );
}

export default AddProductForm;
