import React from 'react';
import { Route, Routes, Redirect } from 'react-router-dom';
import Cart from './containers/cart';
import ItemList from './containers/itemlist';

export const AppRoutes = () => {
    return (
        <div>
            <Routes>
                <Route path="/" element={<ItemList />}></Route>
                <Route path="/cart" element={<Cart />}></Route>
            </Routes>
        </div>
    )
}