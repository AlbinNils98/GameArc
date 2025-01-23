export default function alterSize(coverUrl: string | null): string {
  if (coverUrl && coverUrl !== '') {
    return coverUrl.replace('thumb', '720p');
  }
  return 'https://placehold.co/720x1280?text=No+image+found';
}
