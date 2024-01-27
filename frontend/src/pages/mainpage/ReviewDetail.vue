<template>
  <link
    rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
  />
  <div class="review-container">
    <div class="user-profile">
      <img src="@\img\default_profile.png" alt="프로필 사진" class="profile-picture" />
      <span class="username">사용자 닉네임</span>
    </div>
    <div class="star-rating">
      <i class="fas fa-star"></i>
      <i class="fas fa-star"></i>
      <i class="fas fa-star"></i>
      <i class="fas fa-star"></i>
      <i class="fas fa-star-half-alt"></i>
    </div>
    <div class="user-review">
      <p class="review-text">
        별점 리뷰 내용이 길어지면 어떻게될까요 어떻게 되긴요 항상 이렇게 되는거죠
        라라라라라라라라라루루루루루라라라라
      </p>
    </div>
  </div>
</template>

<style>
.review-container {
  width: 200px;
  height: 280px;
  border-left: 2px solid #ccc;
  border-right: 2px solid #ccc;
  border-radius: 8px;
  padding: 16px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.user-profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 12px;
}

.profile-picture {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 50%;
  margin-bottom: 8px;
}

.username {
  font-weight: bold;
}

.user-review {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-grow: 1;
}

.star-rating {
  display: flex;
  align-items: center;
  justify-content: center;
  color: #ffd700;
  margin-bottom: 8px;
}

.star-rating i {
  margin-right: 4px;
  font-size: 16px;
}

.review-text {
  font-size: 14px;
  margin: 0;
}
</style>
