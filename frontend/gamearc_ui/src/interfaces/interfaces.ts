export interface IUser {
  username: string | null;
}

export interface IGame {
  title: string,
  description: string,
  cover: string,
  genres: string[]
}

export interface IUserGame {
  id: number,
  game: IGame,
  comment: string | null,
  status: string,
  storyRating: number,
  graphicsRating: number,
  gameplayRating: number,
}