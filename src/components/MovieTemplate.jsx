import React, { Component } from 'react';
import { Grid, Col, Row } from 'react-bootstrap';
import '../Styles/about.css';
import apiClient from '../api'; // API 경로에 맞게 조정

class MovieTemplate extends Component {
  state = {
    movie: null,
    thumbnailImageUrl: null,
    actorImageUrls: {},
    loading: true,
    error: null,
  };

  componentDidMount() {
    const { movieId } = this.props;

    // 영화 데이터 및 썸네일 이미지 로드
    apiClient
      .get(`/api/movies/${movieId}`)
      .then((response) => {
        const movie = response.data.data;
        this.setState({ movie });

        // 썸네일 이미지 요청
        return apiClient.get(`/api/images/${movie.thumbnailImageId}`, { responseType: 'blob' });
      })
      .then((thumbnailResponse) => {
        const thumbnailImageUrl = URL.createObjectURL(thumbnailResponse.data);
        this.setState({ thumbnailImageUrl, loading: false });

        // 각 배우의 프로필 이미지 요청
        const cast = this.state.movie.cast || [];
        cast.forEach((actor) => {
          this.fetchActorImage(actor.profileImageId);
        });
      })
      .catch((error) => {
        this.setState({ error: error.message, loading: false });
      });
  }

  fetchActorImage = (profileImageId) => {
    apiClient
      .get(`/api/images/${profileImageId}`, { responseType: 'blob' })
      .then((response) => {
        const actorImageUrl = URL.createObjectURL(response.data);
        this.setState((prevState) => ({
          actorImageUrls: {
            ...prevState.actorImageUrls,
            [profileImageId]: actorImageUrl,
          },
        }));
      })
      .catch((error) => {
        console.error(`Failed to fetch image for profileImageId: ${profileImageId}`, error);
      });
  };

  render() {
    const { movie, thumbnailImageUrl, actorImageUrls, loading, error } = this.state;

    if (loading) return <p>로딩 중...</p>;
    if (error) return <p>에러 발생: {error}</p>;

    const { title, trailerUrl, description, cast } = movie;

    return (
      <div>
        {/* 영화 정보 섹션 */}
        <div className="content-wrapper">
          {thumbnailImageUrl && <img src={thumbnailImageUrl} alt={`${title} 썸네일`} className="movie-thumbnail" />}
          <h2 className="heading">{title}</h2>
        </div>

        <Grid>
          <Col xs={12} sm={8} smOffset={2}>
            <iframe
              width="560"
              height="315"
              src={trailerUrl}
              title={`${title} Trailer`}
              frameBorder="0"
              allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
              allowFullScreen
            ></iframe>
            <h3>영화 설명</h3>
            <p>{description}</p>
          </Col>
        </Grid>

        {/* 출연진 정보 섹션 */}
        <Grid fluid className="team">
          <h1 className="text-center">출연진</h1>
          <h5 className="text-center">주연 및 조연</h5>
          <Grid>
            <Row className="show-grid text-center">
              {cast.map((actor) => (
                <Col xs={12} sm={4} className="person-wrapper" key={actor.id}>
                  {actorImageUrls[actor.profileImageId] ? (
                    <img
                      src={actorImageUrls[actor.profileImageId]}
                      alt={`${actor.name} 프로필`}
                      className="profile-pic"
                    />
                  ) : (
                    <p>이미지 로딩 중...</p>
                  )}
                  <h3>{actor.name}</h3>
                  <h4>{actor.role}</h4>
                  <p>{actor.description}</p>
                </Col>
              ))}
            </Row>
          </Grid>
        </Grid>
      </div>
    );
  }
}

export default MovieTemplate;
