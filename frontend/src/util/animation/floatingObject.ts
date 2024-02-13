import gsap from 'gsap'
import randomPosition from '@/util/animation/randomPosition'

// TODO: randomPosition 값 환경 변수나 다른 변수들로 빼기

function floatingObject(selector: string, delay: number, size: number): void {
  // gsap.to(요소, 시간, 옵션);
  gsap.to(
    selector,
    randomPosition(1.5, 2.5), //최소값 , 최대값
    {
      y: 20, //y축으로 20내려옴
      repeat: -1, // 무한반복
      yoyo: true, //위에서 아래로 내려가는 것 , 애니메이션이 한번 진행 후 다시 돌아옴
      ease: Power1.easeInOut, //자연스럽게 보이게 하는 것
      delay: randomPosition(0, 0)
    }
  )
}
export default floatingObject
