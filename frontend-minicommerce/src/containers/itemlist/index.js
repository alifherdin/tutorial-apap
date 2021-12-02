import React, { Component } from "react";
import Item from "../../components/item";
import classes from "./styles.modules.css";
import APIConfig from "../../api/APIConfig";
import Button from '../../components/button/';
import Modal from '../../components/modal';
import { Badge } from '@mui/material';
import Fab from '@mui/material/Fab';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Link } from 'react-router-dom';


class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoading: false,
            isCreate: false,
            isEdit: false,
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0,
            searching: false,
            searchItems: [],
            jmlCartItems: 0
        };
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.handleEditItem = this.handleEditItem.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.handleSearch = this.handleSearch.bind(this);
        this.handleAddtoCart = this.handleAddtoCart.bind(this);
    }

    componentDidMount() {
        this.loadData();
    }

    async loadData() {
        try {
            const { data } = await APIConfig.get("/item");
            this.setState({ items: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }

        this.loadJumlahItemCart();
    }


    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }

    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }

    handleAddItem() {
        this.setState({
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0
        })
        this.setState({ isCreate: true });
    }

    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate: false, isEdit: false });
    }

    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
            this.loadData();
            this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }

    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
        this.setState({
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0
        })
    }

    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate: false, isEdit: false });
    }

    async loadSearchData(text) {
        try {
            const { data } = await APIConfig.get("/item?title=" + text);
            console.log(text)
            this.setState({ searchItems: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    handleSearch(event) {
        if (event.target.value === null || event.target.value.length === 0) {
            this.setState({ searching: false });
        } else {
            this.loadSearchData(event.target.value);
            this.setState({ searching: true });
        }
    }

    async sendAddToCart(body) {
        try {
            const { data } = await APIConfig.post("/cart", body);
            this.setState({ searchItems: data.result });
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.loadJumlahItemCart();
    }

    handleAddtoCart(item) {
        const jumlah = document.getElementById("item=" + item.id).value;
        const reqBody = [
            {
                "idItem": item.id,
                "quantity": jumlah
            }
        ]
        this.loadData();
        if (jumlah <= item.quantity) {
            this.sendAddToCart(reqBody[0]);
            this.loadData();
            
        } else {
            alert("Stok tidak cukup!");
        }
    }

    async loadJumlahItemCart() {
        try {
            const { data } = await APIConfig.get("/cart");
            this.setState({ jmlCartItems: data.result.length });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    render() {
        console.log("render()");
        return (
            <div className='itemList'>
                <h1 className='title'>All Items</h1>
                <div style={{ position: 'fixed', top: 25, right: 25 }}>
                    <Link to={'/cart'}>
                        <Fab variant="extended">
                            <Badge color="secondary" badgeContent={this.state.jmlCartItems}>
                                <ShoppingCartIcon />
                            </Badge>
                        </Fab>
                    </Link>
                </div>
                <div>
                    <input
                        type="text"
                        className='textField'
                        onChange={this.handleSearch}
                        placeholder="Cari berdasarkan nama item"
                        name="searchText"
                    />
                </div>

                {this.state.searching ? (
                    <div>
                        {this.state.searchItems.map((item) => (
                            <Item
                                key={item.id}
                                id={item.id}
                                title={item.title}
                                price={item.price}
                                description={item.description}
                                category={item.category}
                                quantity={item.quantity}
                                handleEdit={() => this.handleEditItem(item)}
                                handleAddtoCart={() => this.handleAddtoCart(item)}
                            />
                        ))}
                    </div>
                ) : <div>
                    <div>
                        <Button action={this.handleAddItem}> Add Item </Button>
                        {this.state.items.map((item) => (
                            <Item
                                key={item.id}
                                id={item.id}
                                title={item.title}
                                price={item.price}
                                description={item.description}
                                category={item.category}
                                quantity={item.quantity}
                                handleEdit={() => this.handleEditItem(item)}
                                handleAddtoCart={() => this.handleAddtoCart(item)}
                            />
                        ))}
                    </div>
                </div>
                }
                <Modal
                    show={this.state.isCreate || this.state.isEdit}
                    handleCloseModal={this.handleCancel}
                    modalTitle={this.state.isCreate
                        ? "Add Item"
                        : `Edit Item ID ${this.state.id}`}>
                    <form>
                        <input
                            className='textField'
                            type="text"
                            placeholder="Nama Item"
                            name="title"
                            value={this.state.title}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className='textField'
                            type="number"
                            placeholder="Harga"
                            name="price"
                            value={this.state.price}
                            onChange={this.handleChangeField}
                        />
                        <textarea
                            className='textField'
                            placeholder="Deskripsi"
                            name="description"
                            rows="4"
                            value={this.state.description}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className='textField'
                            type="text"
                            placeholder="Kategori"
                            name="category"
                            value={this.state.category}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className='textField'
                            type="number"
                            placeholder="qty"
                            name="quantity"
                            value={this.state.quantity}
                            onChange={this.handleChangeField}
                        />
                        <Button action={this.state.isCreate
                            ? this.handleSubmitItem
                            : this.handleSubmitEditItem}
                        >
                            Create/Edit
                        </Button>
                        <Button action={this.handleCancel}>
                            Cancel
                        </Button>
                    </form>
                </Modal>
            </div>
        );
    }
}
export default ItemList;