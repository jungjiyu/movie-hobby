import axios from 'axios';

// Axios 인스턴스 생성
const apiClient = axios.create({
  baseURL: 'http://localhost:8080', // 서버의 기본 URL (필요에 따라 변경)
  timeout: 5000, // 요청 제한 시간 (ms)
  headers: {
    'Content-Type': 'application/json',
  },
});

export default apiClient;
