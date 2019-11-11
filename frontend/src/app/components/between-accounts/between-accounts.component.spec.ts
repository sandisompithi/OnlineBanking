import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BetweenAccountsComponent } from './between-accounts.component';

describe('BetweenAccountsComponent', () => {
  let component: BetweenAccountsComponent;
  let fixture: ComponentFixture<BetweenAccountsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BetweenAccountsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BetweenAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
