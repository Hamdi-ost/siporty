import { DonationsService } from './../../services/donations.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-informations',
  templateUrl: './informations.component.html',
  styleUrls: ['./informations.component.css']
})
export class InformationsComponent implements OnInit {
  title = 'Informations';
  infos = [];
  columnsName = ['username', 'name account', 'bank', 'agency', 'rib', 'amount'];

  constructor(private donationService: DonationsService) { }

  ngOnInit() {
    this.donationService.getAllDonations()
      .subscribe(donations => {
        const inf = [];
        inf.push(donations);
        console.log(inf[0]);
        inf[0].forEach(el => {
          const info = {
            username: el['userInfo'].username,
            'name account': el['userInfo'].accountName || 'Not Set yet',
            bank: el['userInfo'].banque || 'Not Set yet',
            agency: el['userInfo'].agence || 'Not Set yet',
            rib: el['userInfo'].ccb || 'Not Set yet',
            amount: el['solde']
          };
          this.infos.push(info);
          this.infos.reverse();
          console.log(this.infos);
        });
      });
  }

}
