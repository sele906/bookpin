const API_URL = import.meta.env.VITE_API_URL || ''

export async function getConnection() {
    const response = await fetch(`${API_URL}/api/connection`)

    console.log(response);

    if (!response.ok) {
        throw new Error('도서관 조회 실패');
    }

    return response.json();
}