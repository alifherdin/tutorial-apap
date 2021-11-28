import React from 'react';
import listItems from "../../items.json"
import List from "../../components/List/index";
import "./index.css"
import { Badge } from '@mui/material';
import Fab from '@mui/material/Fab';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import ViewStreamIcon from '@mui/icons-material/ViewStream';


export default class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            shopItems: listItems,
            cartItems: [],
            cartHidden: true,
            balance: 120,
        };
    }

    handleAddItemToCart = (item) => {
        const newItems = [...this.state.cartItems]
        const newItem = { ...item };
        const targetInd = newItems.findIndex((it) => it.id === newItem.id);
        const hargaBrg = newItem.price;
        const newBalance = parseFloat((this.state.balance - hargaBrg).toFixed(3));

        if (+newBalance >= 0) {
            if (targetInd < 0) {
                newItem.inCart = true;
                newItems.push(newItem);
                this.updateShopItem(newItem, true)
            }
            this.setState({ cartItems: newItems });
            this.setState({ balance: newBalance });
        } else {
            alert("Balance not sufficient!")
        }
    };

    updateShopItem = (item, inCart) => {
        const tempShopItems = this.state.shopItems;
        const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
        tempShopItems[targetInd].inCart = inCart;
        this.setState({ shopItems: tempShopItems });
    }

    handleToggle = () => {
        const cartHidden = this.state.cartHidden;
        this.setState({ cartHidden: !cartHidden })
    }

    handleDeleteItemFromCart = (item) => {
        const cItems = [...this.state.cartItems]
        const cItem = { ...item };
        const targetInd = cItems.findIndex((it) => it.id === cItem.id);
        const hargaBrg = cItem.price;

        if (targetInd >= 0) {
            cItem.inCart = false;
            cItems.splice(targetInd, 1);
            const tempShopItems = this.state.shopItems;
            const targetIdx = tempShopItems.findIndex((it) => it.id === cItem.id);
            tempShopItems[targetIdx].inCart = false;
            this.setState({ shopItems: tempShopItems });
        }

        const newBalance = parseFloat((+this.state.balance + +hargaBrg).toFixed(3));

        this.setState({ balance: newBalance });
        this.setState({ cartItems: cItems });
    }

    render() {
        return (
            <div className="container-fluid">
                <h1 className="text-center mt-3 mb-0">Mini Commerce</h1>
                <div style={{ position: 'fixed', top: 25, right: 25 }}>
                    <Fab variant="extended" onClick={this.handleToggle}>
                        {this.state.cartHidden ?
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                            : <ViewStreamIcon />
                        }
                    </Fab>
                </div>
                <p className="text-center text-secondary text-sm font-italic">
                    (this is a <strong>class-based</strong> application)
                </p>
                <p className="text-center text-primary">Your Balance: <b>{this.state.balance}</b></p>
                <div className="container pt-3">
                    <div className="row mt-3">
                        {!this.state.cartHidden ? (
                            <div className="col-sm">
                                <List
                                    title="My Cart"
                                    items={this.state.cartItems}
                                    onItemClick={this.handleDeleteItemFromCart}>
                                </List>
                            </div>
                        ) : <div className="col-sm">
                            <List
                                title="List Items"
                                items={this.state.shopItems}
                                onItemClick={this.handleAddItemToCart}
                                isShopList={true}>
                            </List>
                        </div>}
                    </div>
                </div>
            </div>
        )
    }
}