export default interface User {
    id: number | undefined,
    firstName: String;
    lastName: String
    email: String;
    birthday?: String | undefined;
    login: String;
    password: String;
    phone: String;
}