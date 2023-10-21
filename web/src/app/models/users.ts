export default interface User {
    firstName: String;
    lastName: String
    email: String;
    birthday?: String | undefined;
    login: String;
    password: String;
    phone: String;
}