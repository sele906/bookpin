
export async function getConnection() {
    const response = await fetch('/api/connection');

    console.log(response);

    if (!response.ok) {
        throw new Error('도서관 조회 실패');
    }

    return response.json();
}