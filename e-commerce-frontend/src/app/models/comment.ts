import { ProductID } from "./product";

export interface Comment {
    id_product: string;
    idUser: string;
    text: string;
    commentDate: string;
}

export interface newComment {
    idUser: string;
    text: string;
    product: ProductID;
}