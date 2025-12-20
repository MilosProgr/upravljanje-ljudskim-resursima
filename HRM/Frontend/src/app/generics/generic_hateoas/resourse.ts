import { Link } from "./link";

export interface Resourse<T> {
    data: T;
    links?: Link[];
}