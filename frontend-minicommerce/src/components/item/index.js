import React from "react";
import Button from "../button";


const Item = (props) => {
    const { id, title, price, description, category, handleEdit, quantity, handleDelete, value, handleAddtoCart } = props;
    return (
        <div className='item'>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p>{`stok: ${quantity}`}</p>
            <Button action={handleEdit}> Edit barang </Button>
            <input className='textField' 
                type='number' 
                placeholder='Masukkan jumlah barang yang ingin anda beli....' 
                name='count' 
                value={value}
                id={`item=${id}`}>
            </input>
            <Button action={handleAddtoCart}>Add to Cart</Button>
        </div>
    );
};

export default Item;