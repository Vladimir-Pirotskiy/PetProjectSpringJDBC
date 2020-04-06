export interface TransactionsDTO {
    id?: string;
    amount?: number;
    date?: string;
    familyMember?: string;
    income?: string;
    outcome?: string;
}

export interface PostResponse {
    text: string;
    title: string;
    dateTime: string;
}
