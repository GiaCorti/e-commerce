import { Product } from "./product"

export interface Cart{
    idCart: number
    idUser: string
    qty: number
    completed: boolean
    product: Product
}