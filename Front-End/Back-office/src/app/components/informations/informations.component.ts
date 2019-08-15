import { DonationsService } from './../../services/donations.service';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services';

@Component({
  selector: 'app-informations',
  templateUrl: './informations.component.html',
  styleUrls: ['./informations.component.css']
})
export class InformationsComponent implements OnInit {
  title = 'Informations';
  infos;
  columnsName = ['username', 'name account', 'bank', 'agency', 'rib', 'amount','phone'];

  constructor(private donationService: DonationsService, private userService: UserService) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData() {
    this.infos = [];
    this.donationService.getAllDonations()
      .subscribe(donations => {
        const inf = [];
        inf.push(donations);
        inf[0].forEach(el => {
          console.log(el['userInfo'].phone);
          const info = {
            username: el['userInfo'].username,
            'name account': el['userInfo'].accountName || 'Not Set yet',
            bank: el['userInfo'].banque || 'Not Set yet',
            agency: el['userInfo'].agence || 'Not Set yet',
            phone : el['userInfo'].phone || 'Not Set yet',
            rib: el['userInfo'].ccb || 'Not Set yet',
            amount: el['solde']
          };

          this.infos.push(info);
          this.infos.reverse();
        });
      });
  }

  resetSolde(user) {
    confirm('Are You Sure ?')
    this.userService.getAllUsers().subscribe(users => {
      console.log(user)
      const userToReset = users.filter(el => el.username == user[0].username);
      console.log(userToReset)
      this.donationService.resetSolde(userToReset[0].id)
        .subscribe(data => this.fetchData());
    })
  }

}
