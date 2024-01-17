function randomPosition(min: number, max: number): number {
  return parseFloat((Math.random() * (max - min) + min).toFixed(2))
}
export default randomPosition
