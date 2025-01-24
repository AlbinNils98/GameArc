import { IUserGame } from "../interfaces/interfaces"

const getAverageRating = (userGame: IUserGame) => {
  
  const average = Math.round((userGame.storyRating + userGame.gameplayRating + userGame.graphicsRating) / 3);

  return average;
}

export default getAverageRating;