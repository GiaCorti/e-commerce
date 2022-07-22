import { Cart } from "./cart"

export interface Order {
    id: number
    idOrder: string
    cart: Cart
    orderDate: Date
    total: number
}