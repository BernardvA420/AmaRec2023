/*function trackAndRedirect(userid, id, genre, mood, referralLink, title, artist, song_cover) {
 const data = {
 userid: userid,
 songid: id,
 genre: genre,
 mood: mood,
 title: title,
 artist: artist,
 song_cover: song_cover,
 songlink: referralLink
 };

 // Make an HTTP POST request to the server to save the data
 fetch('/track-interaction', {
   method: 'POST',
   headers: {
   'Content-Type': 'application/json'
   },
   body: JSON.stringify(data)
   })
   .then(response => {
     if (!response.ok) {
       throw new Error('Network response was not ok');
     }
     // Handle the successful response if needed
     //console.log('Interaction data successfully tracked and stored in the database.');
      // Manually redirect to the clicked link
      //window.open(referralLink, '_blank');
   })
   .catch(error => {
     // Handle any errors that occurred during the fetch request
     console.error('Error occurred while tracking interaction:', error);
 });
}*/

function likeSong(songId) {
 fetch('/playlists/addToLikedSongs?songId=' + songId, {
   method: 'POST',
 })
 .then(response => {
   if (response.ok) {
     alert('Song liked and added to "Liked Songs" playlist.');
   } else {
     alert('Failed to like the song.');
   }
 })
 .catch(error => {
    console.error('Error occurred while liking the song:', error);
 });
}

function promptForPlaylistAndAddSong(songId) {
   const playlistName = prompt("Enter the playlist name:");
   if (playlistName === null) {
      return;
   }

   fetch('/playlists/' + playlistName + '/addSong?songId=' + songId, {
      method: 'POST',
   })
   .then(response => {
      if (response.ok) {
         alert('Song added to the playlist successfully.');
      } else {
         alert('Failed to add the song to the playlist.');
      }
   })
   .catch(error => {
       console.error('Error occurred while adding the song to the playlist:', error);
   });
}