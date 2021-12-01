import React from "react";
import "./styles.modules.css";


const CartItem = (props) => {
    const { id, title, price, description, category, handleEdit, quantity, handleDelete, totalHarga } = props;
    return (
        <div className='item'>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p>{`Jumlah: ${quantity}`}</p>
            <p>{`Total Harga: ${totalHarga}`}</p>
        </div>
    );
};
export default CartItem;