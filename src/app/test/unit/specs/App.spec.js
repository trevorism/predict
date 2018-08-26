import Vue from 'vue'
import Buefy from 'buefy'
import App from '@/App'

describe('App.vue', () => {
  Vue.use(Buefy)

  it('renders its name and description', () => {
    const Constructor = Vue.extend(App)
    const buttonForm = new Constructor().$mount()
    expect(buttonForm.$el.textContent).to.contains('How good are you at telling the future?')
  })
})
